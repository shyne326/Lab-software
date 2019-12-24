import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormArray, FormGroup, FormControl } from '@angular/forms';
import { Sample } from 'src/app/models/sample.model';
import { Result } from 'src/app/models/result.model';
import { SampleService } from 'src/app/services/sample.service';
import { ResultService } from 'src/app/services/result.service';
import { ReactifService } from 'src/app/services/reactif.service';
import { Reactif } from 'src/app/models/reactif.model';

@Component({
  selector: 'app-create-result',
  templateUrl: './create-result.component.html',
  styleUrls: ['./create-result.component.scss']
})
export class CreateResultComponent implements OnInit {

  form: FormGroup;
  sample: Sample = null;
  results: Result[] = [];

  today = new Date().toDateString();
  reactifs: Reactif[] = [];

  count: number = 0;


  constructor(private fb: FormBuilder, private route: ActivatedRoute,
    private sampleService: SampleService, private resultService: ResultService,
    private reactifService: ReactifService,
    private _router: Router) {

    if (this._router.getCurrentNavigation().extras.state) {
      this.sample = this._router.getCurrentNavigation().extras.state.sample as Sample;
    }
  }

  ngOnInit() {


    /*
      this.route.paramMap.subscribe((params) => {
  
        const samp = params.get('sampleId'); console.log(samp);
  
        if(samp){
  
          this.sampleService.getSampleById(+samp).subscribe(
            (response:Sample) => {this.sample = response;
                                  console.log(this.sample)
                                 },
            (err) =>  console.log(err)
          );
  
        }
        this.deviceService.getDevices().subscribe(
          (response: Device[]) => {this.devices = response; console.log(response)},
          err =>console.log(err)
        );
  
      });
      

    this.reactifService.getReactifs().subscribe(
      (response: Reactif[]) => { this.reactifs = response; console.log(response) },
      err => console.log(err)
    );


    this.form = this.fb.group({
      testsArray: this.fb.array([])
    });

    this.addTestInputs();
  }

// The methods below will be temporarily unavailable since they won't be used
 /* 
 public addTestInputs() {
    if (this.sample.results.length <= 0) {
      console.log("This is in the addTests method called from ngDoCheck " + this.sample);
      // (<FormArray>this.form.get('testsArray')).clear();
      this.sample.tests.forEach((o, i) => {
        const group = this.fb.group({

          resultat: [],
          attachedFile: [''],
          testId: [o.id],
          sampleId: [this.sample.id],
          validated: [false],
          validatorId: [1],
          reactif: ['']
        });
        (this.form.controls.testsArray as FormArray).push(group);
      });
    }
    else {
      this.form.setControl('testsArray', this.setExistingResults(this.sample.results));
      // this.setExistingResults(this.sample.results);

      /*  this.form.patchValue({

          resultat : this.sample.results[0].test. //this.sample.  //??? // Don't know yet what to do
        });    
    }

  }

  public setExistingResults(results: Result[]): FormArray {

    const formArray = new FormArray([]);

    this.sample.tests.forEach((o, j) => {

      let group;

      let found = false;
      for (const r of this.sample.results) {
        if (r.test.id == o.id) {
          formArray.push(
            this.fb.group({

              id: r.id,
        
              resultat: r.resultat,
              attachedFile: r.attachedFile,
              testId: [o.id],
              sampleId: [this.sample.id],
              validated: [false],
              validatorId: [1], // To be changed
              reactif: ['']
            })
          );

          found = true;
        }
      }

      if (!found) {

        formArray.push(
          this.fb.group({

            
            resultat: [],
            attachedFile: [''],
            testId: [o.id],
            sampleId: [this.sample.id],
            validated: [false],
            validatorId: [1],
            reactif: ['']
          })
        );

      }

      //  (this.form.controls.testsArray as FormArray).push(group);
    });

    return formArray;
  }

 //  End of setExistingResults metod

  public onSubmit() {

    this.mapFormValuesToResultModel();   
    if (this.sample.results.length <= 0) {
      this.resultService.addResult(this.results).subscribe(
        () => this._router.navigate(['/result-intermediate/sample/' + this.sample.id]),
        err => console.log(err)
      );
    }
    else{
      this.sample.results = this.results;
      this.resultService.updateResults(this.results).subscribe(
          
         (s) => this._router.navigate(['/result-intermediate/sample/' + this.sample.id]),
         err => console.log(err)
      );
    }
  }

  public mapFormValuesToResultModel() {

    this.results = this.form.value.testsArray.map((v, j) => this.results[j] = v)
    //.filter(v => v !== null))
    console.log(this.form.value.testsArray);
  }

  */
 }
}
