import { Component, OnInit } from '@angular/core';
import { Sample } from 'src/app/models/sample.model';
import { SampleService } from 'src/app/services/sample.service';
import { ActivatedRoute } from '@angular/router';
import { UploadService } from 'src/app/services/upload.service';

@Component({
  selector: 'app-validate-results',
  templateUrl: './validate-results.component.html',
  styleUrls: ['./validate-results.component.scss']
})
export class ValidateResultsComponent implements OnInit {
  
  sample: Sample;
  successMessage: string = '';
  fileToUpload: File;
  downloaded: boolean = false;

  constructor(private sampleService: SampleService, private route: ActivatedRoute, private fileUploadService: UploadService) { }

  ngOnInit() {
     this.route.paramMap.subscribe(params => {

        const samp = params.get('sampleId');
      
        if(samp){
          this.sampleService.getSampleById(+samp).subscribe(

              (response: Sample) => {this.sample = response; console.log(response)},
              (err) => console.log(err)
          );
        }
     });
    
  }

  public validate(){
     this.sample.results.validated = true;
     this.sampleService.updateSample(this.sample).subscribe(
       () => {
                
       },
       err => console.log(err)
     )
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

  public downloade(){

    this.downloaded = true;
  }


}
