import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationExtras } from '@angular/router';
import { SampleService } from 'src/app/services/sample.service';
import { Sample } from 'src/app/models/sample.model';
import { Section } from 'src/app/models/section.model';
import { map } from 'rxjs/operators';
import { Test } from 'src/app/models/test.model';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ResultService } from 'src/app/services/result.service';
import { UploadService } from 'src/app/services/upload.service';

@Component({
  selector: 'app-result-intermediate',
  templateUrl: './result-intermediate.component.html',
  styleUrls: ['./result-intermediate.component.scss']
})
export class ResultIntermediateComponent implements OnInit {

  sample: Sample;
  sections: Section[];
  map : Map<string, Section>;
  successMessage: string = '';

  // Used to test file upload
  fileForm: FormGroup;
  uploadResponse = { status: '', message: '', filePath: '' };
  fileToUpload: File = null;

  constructor(private _router: Router, private route: ActivatedRoute, private sampleService: SampleService, private fb: FormBuilder,
                       private resultService: ResultService, private fileUploadService: UploadService) 
  { 
    this.sections = [];
    this.map = new Map<string, Section>();
  }

  ngOnInit() {

    this.route.paramMap.subscribe((params) => {

      const samp = params.get('sampleId');

      if(samp){

        this.sampleService.getSampleById(+samp).subscribe(
          (response: Sample) => {
                                  this.sample=response;
                                  console.log(this.sample);
                                  this.listSections();
                                },

          err => console.log(err)
        );
      }

    });


     this.fileForm = this.fb.group({
        avatar: ['']
      });
    //this.listSections();
  }

  public listSections(){

    console.log(this.sample.tests);
    for(const t of this.sample.tests){

      //this.sections.push(t.section);
      if(!this.map.has(t.section.name)){
        this.map.set(t.section.name, t.section);
        this.sections.push(t.section);
      }
    }

  // this.map.forEach((s,k, map) => {
  //   this.sections.push(s);
  // });
   console.log(this.sections);
  
  }

  public listTests(section: Section){

    /****** This block is to filter tests and send only the once belonging to the selected section to the
     * respective create result pages
     */
    let examens:Test[] = [];
    let i=0;
    for(i=0; i<this.sample.tests.length; i++){
      if(this.sample.tests[i].section.id === section.id){
        examens.push(this.sample.tests[i]);
      }
    }
    
    this.sample.tests = examens;

 /*******   End of the filtering *************************************** */

    let extras: NavigationExtras = {
      state:{
        sample: this.sample
      }
 
     }

      if(section.name != 'BACTERIOLOGIE'){
         this._router.navigate(['results/create/sample/' + this.sample.id], extras);
       }
       else{
         this._router.navigate(['results/create/bacterio/sample/' + this.sample.id], extras);
       }
  }


  public handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  public uploadFileToActivity() {
   
    console.log(this.fileToUpload);

    const codeUtilisateurPatient = this.sample.patient.codeUtilisateur;
    const sampleId = this.sample.id;
    const laborantinId = sessionStorage.getItem('userId');

    this.fileUploadService.postFile(this.fileToUpload, sampleId, codeUtilisateurPatient, laborantinId).subscribe(data => {
      
        this.successMessage = 'Resultat attaché avec succès'
      }, error => {
        console.log(error);
      });
  }


}
