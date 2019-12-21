import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Sample } from 'src/app/models/sample.model';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { SampleService } from 'src/app/services/sample.service';


declare var $;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  samples : Sample[];
  successMessage = "";

  dtOptions : DataTables.Settings = {};
   // We use this trigger because fetching the list of persons can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<any> = new Subject();

  private baseUrl = "http://localhost:8080/";

  private jsonUrl = this.baseUrl+"samples";
  constructor(private http: HttpClient, private _router: Router, private sampleService: SampleService) { 
    if(this._router.getCurrentNavigation().extras.state){
       this.successMessage = this._router.getCurrentNavigation().extras.state.message;
    }
  }

  @ViewChild('samplesTable', {static:true}) samplesTable: ElementRef;
  dataTable: any;
  ngOnInit() {

    this.dtOptions = {
      lengthMenu:[[5, 10, 15, 20, -1],['5 Records', '10 Records', '15 Records', '20 Records', 'All Records Records']],
      pageLength: 10,
   
    }
    
    
//this.http.get(this.jsonUrl,).subscribe(
  this.sampleService.getSamples().subscribe(
      (response: Sample[]) => {
                                 this.samples = response;
                                 console.log(this.samples);
                                 this.dtTrigger.next() },
      
      (err: any) => console.log(err)
    )
  }

  /*
  public computeTestsEnAttentes(sample:any) {
    

     let count = 0;
                        if((count =sample.tests.length - sample.results.length) > 0){
                            return count;
                        }
                        return  count;
    
  }

  public computeEnAttenteDeValidation(sample: any){

    var count = 0;
    let data: Sample = sample;
    let len: number = data.results.length;

    if(data.results != null || len>0){
      
      for(var i=0; i<len; i++){

        if(!data.results[i].validated){

          count++;  
        //  console.log(len);       
        }
      } 
      return count;
      
 
    }

  }
*/
  public computeTotal(sample: any){

    return (sample.tests.length)? sample.tests.length :0;
  }

  

  public completed(sample){
   
    /*
   
    let total = this.computeTotal(sample);
    if( total-(this.computeTestsEnAttentes(sample) + this.computeEnAttenteDeValidation(sample)) == total){
       return 'Oui';
    }
    return 'Non';

    */

    if(sample.result != null && sample.results.validated){
       return 'Oui';
    }
    return 'Non'
  }
  

  public hasAuthority(): boolean{

     if(sessionStorage.getItem('userRole') == 'ROLE_DT')
        return true;
     return false;
  }
}
