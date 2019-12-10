import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

declare var $;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  title = 'tuto';
  data: any;

  public constructor(private loginService: AuthenticationService){}


  public ngOnInit(){

  }
}
