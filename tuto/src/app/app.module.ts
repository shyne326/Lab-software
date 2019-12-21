import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PatientService } from './services/patient.service';
import { UserService } from './services/user.service';
import { EmployeeService } from './services/employee.service';
import { DeviceService } from './services/device.service';
import { SampleService } from './services/sample.service';
import { DashboardComponent } from './components/master/dashboard/dashboard.component';
import { AdminMasterComponent } from './components/master/admin-master/admin-master.component';
import { LoginComponent } from './components/login/login.component';
import { MasterComponent } from './components/master/master.component';
import { CreateResultComponent } from './components/master/create-result/create-result.component';
import { CreatePatientComponent } from './components/master/create-patient/create-patient.component';
import { CreateSampleComponent } from './components/master/create-sample/create-sample.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ListPatientsComponent } from './components/master/list-patients/list-patients.component';
import { DataTablesModule } from 'angular-datatables';
import { LogoutComponent } from './logout/logout.component';
import { UniteComponent } from './components/master/unite/unite.component';
import { ResultIntermediateComponent } from './components/master/result-intermediate/result-intermediate.component';
import { ValidateResultsComponent } from './components/master/validate-results/validate-results.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AdminMasterComponent,
    LoginComponent,
    MasterComponent,
    CreateResultComponent,
    CreatePatientComponent,
    CreateSampleComponent,
    ListPatientsComponent,
    LogoutComponent,
    UniteComponent,
    ResultIntermediateComponent,
    ValidateResultsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    DataTablesModule,
    FormsModule
  ],
  providers: [PatientService,
              UserService,
              EmployeeService,
              DeviceService,
              SampleService
            ],
  bootstrap: [AppComponent]
})
export class AppModule { }
