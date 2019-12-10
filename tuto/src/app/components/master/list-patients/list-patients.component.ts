import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { Patient } from 'src/app/models/patient.model';
import { HttpClient } from '@angular/common/http';
import { PatientService } from 'src/app/services/patient.service';

declare var $;

@Component({
  selector: 'app-list-patients',
  templateUrl: './list-patients.component.html',
  styleUrls: ['./list-patients.component.scss']
})
export class ListPatientsComponent implements OnInit {

   patients : Patient[];
   successMessage = "";

   dtOptions : DataTables.Settings = {};
   // We use this trigger because fetching the list of persons can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<any> = new Subject();

  private baseUrl = "http://localhost:8080/";

  private jsonUrl = this.baseUrl+"patients";

 // @ViewChild('tablePatients', {static:true}) tablePatients: ElementRef;
  //dataTable: any;

  age: number;
  constructor(private _router: Router, private patientService: PatientService, private http: HttpClient) { 

    if(this._router.getCurrentNavigation().extras.state){
      this.successMessage = this._router.getCurrentNavigation().extras.state.message;
    }
  }

  ngOnInit() {

    this.dtOptions = {
      lengthMenu:[[5, 10, 15, 20, -1],['5 Records', '10 Records', '15 Records', '20 Records', 'All Records Records']],
      pageLength: 10,
   
    }

   // this.http.get(this.jsonUrl).subscribe(
      this.patientService.getPatients().subscribe(
      (response: Patient[]) => {
                                 this.patients = response;
                                 this.dtTrigger.next() },
      
      (err: any) => console.log(err)
    )
  }

  computeAge(dateOfBirth: Date): string{
     
    let data: any
    data = new Date(dateOfBirth);
    var today : any;
    today = new Date();
    let age = Math.floor((today-data) / (365.25 * 24 * 60 * 60 * 1000));

   return ""+age;
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }


   editButtonClick(patientId){

         this._router.navigate(['/patients/edit', patientId]).then();
  }

  goToAddSamplePage(patientId: number){

    this._router.navigate(['samples/create/patient',patientId]);
  }

}
