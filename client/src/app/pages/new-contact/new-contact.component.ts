import { Component, OnInit } from '@angular/core';
import {ContactService} from "../../services/services/contact.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ContactDto} from "../../services/models/contact-dto";
import {HelperService} from "../../services/helper/helper.service";

@Component({
  selector: 'app-new-contact',
  templateUrl: './new-contact.component.html',
  styleUrls: ['./new-contact.component.scss']
})
export class NewContactComponent implements OnInit {
  contact : ContactDto = {};
  testNew : boolean = false;
  constructor(private contactService : ContactService,
              private router : Router,
              private helperService : HelperService,
              private activatedRoute : ActivatedRoute
  ) { }

  ngOnInit(): void {
    const contactId = this.activatedRoute.snapshot.params['idContact'];
    if(contactId){
      this.testNew = true;
      this.contactService.findById2({
        "contact-id" : contactId
      }).subscribe({
        next : (data)=>{
          this.contact = data
        }
      })
    }
  }

  save() {
    this.contact.userId = this.helperService.userId;
    this.contactService.save2({
      body : this.contact
    }).subscribe({
        next : ()=>{
          this.router.navigate(['user/my-contact-list']);
        },
      error : (err)=>{
        console.log(err);
      }
      });

  }

  cancel() {
    this.router.navigate(['user/my-contact-list']);
  }
}
