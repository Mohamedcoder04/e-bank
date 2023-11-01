import { Component, OnInit } from '@angular/core';
import {UserDto} from "../../services/models/user-dto";
import {UserService} from "../../services/services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-manager-users',
  templateUrl: './manager-users.component.html',
  styleUrls: ['./manager-users.component.scss']
})
export class ManagerUsersComponent implements OnInit {

  users : Array<UserDto> = [];
  userIdToUpdate = -1;
  showInactiveUsers = false;
  updateState : boolean | undefined;

  constructor( private userService : UserService) { }

  ngOnInit(): void {
    this.findAllCustomers();
  }

  private findAllCustomers(){
    this.userService.findAll()
      .subscribe({
        next : (data) => {
          this.users = data;
        }
      })
  }

  filtreInactiveUsers() {
    if(this.showInactiveUsers){
      this.users = this.users.filter((c) => !c.active)
    }else {
      this.findAllCustomers();
    }
  }

  changeUserState(active: boolean | undefined, id: number | undefined) {
    this.userIdToUpdate = id as number;
    this.updateState = active;
  }

  updateUserState() {
    if (this.updateState) {
      this.userService.validateAccount({
        'user-id': this.userIdToUpdate as number
      }).subscribe({
        next: () =>{
          this.findAllCustomers();
        }
      });
    } else {
      this.userService.inValidateAccount({
        'user-id': this.userIdToUpdate as number
      }).subscribe({
        next: () =>{
          this.findAllCustomers();
        }
      });
    }
  }

  cancelUpdate() {
    const user = this.users.find((c) =>c.id === this.userIdToUpdate);
    if (user) {
      user.active = !user.active
    }
    this.userIdToUpdate = -1;
    this.updateState = undefined
  }
}
