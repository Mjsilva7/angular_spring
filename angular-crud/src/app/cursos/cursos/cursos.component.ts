import { Component } from '@angular/core';
import { Curso } from '../model/curso';
import { CursosService } from '../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent {

  cursos: Curso[] = [];
  displayedColumns = ['name', 'category'];

  constructor(private cursosService: CursosService) {
    this.cursos = this.cursosService.list();
  }

}
