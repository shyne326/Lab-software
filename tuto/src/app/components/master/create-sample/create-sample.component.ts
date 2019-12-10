import { Component, OnInit } from '@angular/core';
import { SampleType } from 'src/app/models/sampleType.model';
import { SampleTypeService } from 'src/app/services/sample-type.service';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { Test } from 'src/app/models/test.model';
import { SampleService } from 'src/app/services/sample.service';
import { Sample } from 'src/app/models/sample.model';
import { Router, NavigationExtras, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-sample',
  templateUrl: './create-sample.component.html',
  styleUrls: ['./create-sample.component.scss']
})
export class CreateSampleComponent implements OnInit {

  private sample: Sample = null;

  sampleTypes: SampleType[];
  tests: Test[];


  successMessage = "";



  form: FormGroup;
  //testsForm: FormGroup;

  selectedSampleTypeName = "";

  constructor(private sampleTypeService: SampleTypeService,
                  private fb: FormBuilder,
                   private sampleService: SampleService,
                   private _router: Router,
                   private route: ActivatedRoute) { }

  ngOnInit() {

       this.form = this.fb.group({
           name : ['', Validators.required],
           testsCheckboxes: this.fb.array([]),
           requester: ['', Validators.required],
           note: ['']
        } );

       this.sampleTypeService.getSampleTypes().subscribe(
         (response: SampleType[]) => this.sampleTypes=response,
         (err: any) => console.log(err)
       );

       this.route.paramMap.subscribe((params) => {

          const patient = params.get('patientId');
          if(patient){

            this.sample = {
              id : null,
           //   employee : null,
           //   patient : null,
              requester : "",
           //   sample_type : null,
           //   created_at: null,
           //   updated_at: null,
              testIds : [],
              sampleTypeId: null,
              labTechnicianId : +sessionStorage.getItem('userId'),
              patientId : +patient,
              note: ''
           //   tests : null
            
    }
          }
          else{

            // Here we are on edit
            const id = params.get('id');
            if(id){} 
          }
       });
}

// This function makes appear the list of tests
     // related to the selected sample type
  public listTestsForSelectedSample() {

     
     let selectedId = this.form.value.name;
     console.log(selectedId);
     console.log(this.sampleTypes);

     for(const s of this.sampleTypes){
    
       if(s.id == selectedId){
         this.tests = s.testsThatCanBeConducted;
         this.selectedSampleTypeName = s.name;
         console.log(this.tests);
         break;
       }
     }//(<FormArray>this.form.get('testsCheckboxes')).clear();
   //  (<FormArray>this.form.get('testsCheckboxes')).push(this.addTestsFormGroup());
      this.addTestsToForm();
  
  }

// Helper function delegated with the task of building
   // the form group that will be added in the form array
  public addTestsToForm(){
    (<FormArray>this.form.get('testsCheckboxes')).clear();
    this.tests.forEach((o, i) => {
      const control = new FormControl(); // if first item set to true, else false
      (this.form.controls.testsCheckboxes as FormArray).push(control);
    });

  } 

  public onSubmit(): void{

         let extras: NavigationExtras = {
                    state:{
                      message: "Echantillon ajouté avec succès !"
                    }
               
          }


    const selectedTestsIds = this.form.value.testsCheckboxes
      .map((v, i) => v ? this.tests[i].id : null)
      .filter(v => v !== null);
    console.log(selectedTestsIds);  // This is used during development to make sure an array of selected tests is actually sent

    this.sample.testIds = selectedTestsIds;   // This instruction may be moved to mapFormValueToSampleModel method


      console.log("Before method call");

    this.mapFormValuesToSampleModel();
   // this.sample = this.form.value;
      this.sampleService.addSample(this.sample).subscribe(

        (response) => {this._router.navigate(['/samples'], extras)},
        err => console.log(err)
      );
        
  }

  public mapFormValuesToSampleModel(): void{

      this.sample.id = null;
      this.sample.requester = this.form.value.requester;
      // this.sample.patientId; Already collected in the ngInit() method
      this.sample.sampleTypeId = +this.form.value.name;
      this.sample.note = this.form.value.note;

      console.log("mapped values " + this.form.value);
   //   this.sample.sampleType = this.sampleT
  }

}