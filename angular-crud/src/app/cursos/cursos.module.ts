import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CursosRoutingModule } from './cursos-routing.module';
import { CursosComponent } from './containers/cursos/cursos.component';
import { ImportMaterialModule } from '../shared/import-material/import-material.module';
import { SharedModule } from '../shared/shared.module';
import { CursoFormComponent } from './containers/curso-form/curso-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CursosListaComponent } from './components/cursos-lista/cursos-lista.component';


@NgModule({
  declarations: [
    CursosComponent,
    CursoFormComponent,
    CursosListaComponent
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
