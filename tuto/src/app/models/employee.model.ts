
import { User } from './user.model';
import { Sample } from './sample.model';
import { Deserializable } from './deserializable.model';
import { Result } from './result.model';

export interface Employee extends User {

    salary?: number;
    roles: string;
    conductedSamples?: Sample[];
    validatedResults?: Result[];
  //  conductedSamples: Sample[];

    /*
     deserialize(input: any){
        Object.assign(this, input);
        this.conductedSamples = input.conductedSamples.map(cs => new Sample().deserialize(cs));

        return this;
     }
    */

}