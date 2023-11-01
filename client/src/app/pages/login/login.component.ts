import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {AuthenticationService} from "../../services/services/authentication.service";
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  authRequest : AuthenticationRequest = {
    email:'', password : ''
  };
  errorMessages: Array<string> = [];

  constructor( private router : Router , private authService : AuthenticationService) { }

  ngOnInit(): void {
  }
  /**
   * on peut ajouter "" async "" si on a un code qui va éxecuter après navigate et on veut que naviger tout d'abord
   * après éxecuter le code
   *  et dans la méthode on ajoute "" await ""
   * */
  async register(){
    await this.router.navigate(['register']);
    alert('hi from register method')
  }

  login() {
    this.errorMessages = [];
    this.authService.authenticate({
      body : this.authRequest
    }).subscribe({
      next : async (data)=>{
        localStorage.setItem('token' , data.token as string);
        const helper = new JwtHelperService();
        if (data.token != null) {
          var decodedToken = helper.decodeToken(data.token);
        }
        if(decodedToken.authorities[0].authority === 'ROLE_ADMIN'){
          await this.router.navigate(['admin'])
        }else {
          await this.router.navigate(['user'])
        }
        console.log(decodedToken);
      },
      error : (err)=>{
        this.errorMessages.push(err.error.errorMessage);
        console.log(err);
      }
    })
  }
}
