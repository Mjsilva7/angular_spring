import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CursosRoutingModule } from './cursos-routing.module';
import { CursosComponent } from './cursos/cursos.component';
import { ImportMaterialModule } from '../shared/import-material/import-material.module';
import { SharedModule } from '../shared/shared.module';
import { CursoFormComponent } from './curso-form/curso-form.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CursosComponent,
    CursoFormComponent
  ],
  imports: [
    CommonModule,
    CursosRoutingModule,
    ImportMaterialModule, 
    SharedModule, 
    ReactiveFormsModule
  ]
})
export class CursosModule { }
