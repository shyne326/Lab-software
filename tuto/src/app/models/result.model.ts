import { Employee } from './employee.model';
import { Deserializable } from './deserializable.model';
import { Sample } from './sample.model';
import { Test } from './test.model';

export interface Result
{

    id: number;
  
    resultat: string;
    inference?: string;
    attachedFile?: string;
    validated?: boolean;
    validator?: Employee;
    createdOn?: Date;
    updatedOn?: Date;
    sample: Sample;
    test: Test;
    device?: string;
    reactif?: string;


    //For posting
    testId?: number;
    sampleId?: number;
    validatorId?: number;

   
   /* deserialize(input: any){
        Object.assign(this, input);
        this.validator = input.validator.map(v => new Employee().deserialize(v));
        this.testEffectue = input.testEffectue.map(car => new TestEffectue().deserialize(car));

        return this;
    }
    */

}