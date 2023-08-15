import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CursosRoutingModule } from './cursos-routing.module';
import { CursosComponent } from './cursos/cursos.component';
import { ImportMaterialModule } from '../shared/import-material/import-material.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    CursosComponent
  ],
  imports: [
    CommonModule,
    CursosRoutingModule,
    ImportMaterialModule, 
    SharedModule
  ]
})
export class CursosModule { }
