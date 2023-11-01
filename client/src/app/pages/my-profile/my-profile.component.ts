import { Component, OnInit } from '@angular/core';
import {AddressDto} from "../../services/models/address-dto";
import {UserDto} from "../../services/models/user-dto";
import {HelperService} from "../../services/helper/helper.service";
import {UserService} from "../../services/services/user.service";
import {AddressService} from "../../services/services/address.service";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.scss']
})
export class MyProfileComponent implements OnInit {
  addressDto : AddressDto = {city: '',country: '',houseNumber: 0,street: '',zipCode: 0};
  userDto : UserDto = {email: "", firstName: "", lastName: "", password: ""};

  constructor(
    private helperService : HelperService,
    private userService : UserService,
    private adressService : AddressService

  ) {}

  ngOnInit(): void {
    this.userService.findById({
      "user-id" : this.helperService.userId
    }).subscribe({
      next : (data)=>{
        console.log(data);
        this.userDto = data;
      }
    });
  }

}
