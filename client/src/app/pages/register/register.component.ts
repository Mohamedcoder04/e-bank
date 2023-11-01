import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {UserDto} from "../../services/models/user-dto";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  userDto: UserDto = {
    email: "", firstName: "", lastName: "", password: ""
  }
  errorMessages: Array<string> = [];


  constructor(private router: Router, private authService: AuthenticationService) {
  }

  ngOnInit(): void {
  }

  login() {
    this.router.navigate(['login']);
  }

  register() {
    this.errorMessages = [];
    this.authService.register({
        body: this.userDto
      }
    ).subscribe({
        next: async (data) => {
          await this.router.navigate(['confirm-register'])
        },
        error: (err) => {
          if(err.error.validationErrors != null){
            this.errorMessages = err.error.validationErrors;
          }else {
            this.errorMessages.push(err.error.errorMessage);
          }
          console.log(err);
        }
      }
    )
  }
}
