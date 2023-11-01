import {Component, OnInit} from '@angular/core';
import {TransactionService} from "../../services/services/transaction.service";
import {TransactionDto} from "../../services/models/transaction-dto";
import {HelperService} from "../../services/helper/helper.service";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  transactions: Array<TransactionDto> = [];

  constructor(private transactionService: TransactionService, private helperService : HelperService ) {
  }

  ngOnInit(): void {
    this.getAllTransactions();
  }

  getAllTransactions() {
    this.transactionService.findAllByUserId({
      "user-id" : this.helperService.userId
    }).subscribe({
      next : (data)=>{
        console.log(data);
        this.transactions = data;
      }
    });
  }

}
