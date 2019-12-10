import { Component, OnInit } from '@angular/core';
import { Section } from 'src/app/models/section.model';
import { Subject } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { PatientService } from 'src/app/services/patient.service';
import { HttpClient } from '@angular/common/http';
import { SectionService } from 'src/app/services/section.service';

@Component({
  selector: 'app-unite',
  templateUrl: './unite.component.html',
  styleUrls: ['./unite.component.scss']
})
export class UniteComponent implements OnInit {

  section : Section;

   dtOptions : DataTables.Settings = {};
   // We use this trigger because fetching the list of persons can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<any> = new Subject();

  private baseUrl = "http://localhost:8080/";

  private jsonUrl = this.baseUrl+"patients";

 // @ViewChild('tablePatients', {static:true}) tablePatients: ElementRef;
  //dataTable: any;

  age: number;
  constructor(private _router: Router, private sectionService: SectionService, private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit() {

    this.dtOptions = {
      lengthMenu:[[5, 10, 15, 20, -1],['5 Records', '10 Records', '15 Records', '20 Records', 'All Records Records']],
      pageLength: 10,
   
    }

    this.route.paramMap.subscribe((params) => {
        
      const unite = params.get('unitId');
      this.sectionService.getSectionByName(unite).subscribe(
        (response: Section) => {
                                   this.section = response;
                                   this.dtTrigger.next() },
        
        (err: any) => console.log(err)
      )
    });
   // this.http.get(this.jsonUrl).subscribe(
    
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

}
