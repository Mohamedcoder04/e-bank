import { Component, OnInit } from '@angular/core';
import {TransactionService} from "../../services/services/transaction.service";
import {TransactionDto} from "../../services/models/transaction-dto";
import {ContactDto} from "../../services/models/contact-dto";
import {ContactService} from "../../services/services/contact.service";
import {HelperService} from "../../services/helper/helper.service";
import {StaticsService} from "../../services/services/statics.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-transaction',
  templateUrl: './new-transaction.component.html',
  styleUrls: ['./new-transaction.component.scss']
})
export class NewTransactionComponent implements OnInit {

  transaction : TransactionDto = {};
  contacts : Array<ContactDto> =[];
  accountBalance : number =0;
  errorMessages : Array<string> = [];

  constructor( private transactionService : TransactionService ,
               private contactService : ContactService,
               private helperService : HelperService,
               private staticsService : StaticsService,
               private router: Router
  ) { }

  ngOnInit(): void {
    this.contactService.findAllByUserId1({
      "user-id" : this.helperService.userId
    }).subscribe({
      next : (data)=>{
        this.contacts = data;
      }
    });
    this.staticsService.getAccountBalance({
      "user-id" : this.helperService.userId
    }).subscribe({
      next : (data)=>{
        this.accountBalance = data;
      }
    })
  }

  cancel() {
    this.router.navigate(['user/my-transactions']);
  }

  save() {
    this.transaction.userId = this.helperService.userId;
    this.errorMessages = [];
    this.transactionService.save1({
      body : this.transaction
    }).subscribe({
      next : ()=>{
        this.router.navigate(['user', 'my-transactions'])
      },
      error : (err)=>{
        this.errorMessages = err.error.error;
        console.log(err);
      }
    })
  }
}
