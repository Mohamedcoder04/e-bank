import { Component, OnInit, Input } from '@angular/core';
import {HelperService} from "../../services/helper/helper.service";
import {UserService} from "../../services/services/user.service";
import {AddressService} from "../../services/services/address.service";
import {UserDto} from "../../services/models/user-dto";
import {AddressDto} from "../../services/models/address-dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  username : string = '';
  @Input() isAdmin = false;
  role = 'user';

  constructor(
    private helperService : HelperService,
    private router : Router,
    //private logoutService : Logo

  ) {
  }

  ngOnInit(): void {
    if(this.isAdmin){
      this.role = 'admin';
    }

    this.username = this.helperService.userName;
  }

  logout() {
    this.username = '';
    localStorage.removeItem('token');
    this.router.navigate(['login'])
  }
}
