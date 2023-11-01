import {Component, OnInit, ViewChild} from '@angular/core';
import {LightInfoInput} from "../../components/light-info/light-info.component";
import {TransactionService} from "../../services/services/transaction.service";
import {StaticsService} from "../../services/services/statics.service";
import {HelperService} from "../../services/helper/helper.service";
import {lastValueFrom} from "rxjs";
import {Chart, registerables} from "chart.js";
import {DatepickerOptions} from "ng2-datepicker";
import {DatePipe} from "@angular/common";

Chart.register(...registerables);

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {

  accountInfoList: Array<LightInfoInput> = [];
  accountBalance = 0;
  highestTransfer = 0;
  highestDeposit = 0;
  labels: any[] = [];
  maindata: any[] = [];
  dateOptions: DatepickerOptions = {
    format: 'yyyy-MM-dd'
  };
  startDate: Date = new Date();
  endDate: Date = new Date();
  //@ViewChild('canvas') canvas: any;
  chart: any;

  constructor(
    private transactionService: TransactionService,
    private staticsService: StaticsService,
    private helperService: HelperService,
    private datePipe: DatePipe
  ) {
  }

  ngOnInit(): void {
    this.initializeAccountInfo();
    this.renderChart();
  }

  private async initializeAccountInfo() {
    this.accountBalance = await lastValueFrom(this.staticsService.getAccountBalance({
      "user-id": this.helperService.userId
    }));

    this.highestTransfer = await lastValueFrom(this.staticsService.highestTransfert({
      "user-id": this.helperService.userId
    }));

    this.highestDeposit = await lastValueFrom(this.staticsService.highestDeposit({
      "user-id": this.helperService.userId
    }));

    this.accountInfoList = [
      {
        title: 'Account Balance',
        amount: this.accountBalance,
        infoStyle: 'bg-primary'
      },
      {
        title: 'Highest Transfer',
        amount: this.highestTransfer,
        infoStyle: 'bg-warning'
      },
      {
        title: 'Highest Deposit',
        amount: this.highestDeposit,
        infoStyle: 'bg-success'
      },
    ]
  }

  renderChart() {
    this.chart = new Chart('chartLineId', {
      type: 'line',
      data: {
        labels: this.labels,
        datasets: [{
          label: 'Sum Transactions by day',
          data: this.maindata,
          borderWidth: 1,
          backgroundColor: "pink"
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        },
        plugins: {
          legend: {
            display: true,
            position: "bottom",
            labels: {
              font: {
                size: 16,
                style: "italic"
              },
              usePointStyle: true
            }
          }
        }
      }
    });
  }


  filerStatics() {
    this.maindata = [];
    this.labels = [];
    this.staticsService.findSumTransactionsByDate({
      "user-id": this.helperService.userId,
      "start-date": this.datePipe.transform(this.startDate, "yyyy-MM-dd") as string,
      "end-date": this.datePipe.transform(this.endDate, "yyyy-MM-dd") as string
    }).subscribe({
      next: (values) => {
        for (let recod of values) {
          this.labels.push(recod.transactionDate as string);
          this.maindata.push(recod.amount as number);
        }
        this.chart.data.labels = this.labels;
        this.chart.data.datasets[0].data = this.maindata;
        this.chart.update();
      }
    })
  }
}
