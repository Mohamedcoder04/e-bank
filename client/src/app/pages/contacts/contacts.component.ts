import { Component, OnInit } from '@angular/core';
import {ContactService} from "../../services/services/contact.service";
import {ContactDto} from "../../services/models/contact-dto";
import {HelperService} from "../../services/helper/helper.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.scss']
})
export class ContactsComponent implements OnInit {

  contacts : Array<ContactDto> = [];
  contactIdToDelete : number | undefined = -1;

  constructor( private contactService : ContactService,
               private helperService : HelperService,
               private router : Router
  ) { }

  ngOnInit(): void {
    this.findAllByUserId();
  }

  findAllByUserId(){
    this.contactService.findAllByUserId1({
      "user-id" : this.helperService.userId
    }).subscribe({
      next : (data)=>{
        this.contacts = data}
    });
  }

  update(id: number | undefined) {
    //this.router.navigate(['user/new-contact' , id]); on peut faire aussi
    this.router.navigate(['user','new-contact' , id]);
  }

  delete() {
    if (this.contactIdToDelete) { // >=0 car le 1°ér ID prend 0
      this.contactService.delete2({
        "contact-id": this.contactIdToDelete
      }).subscribe({
        next: () => {
          this.findAllByUserId();
        }
      })
    }
  }
}
