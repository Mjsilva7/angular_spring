import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { CursosService } from '../../services/cursos.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Curso } from '../../model/curso';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss']
})
export class CursoFormComponent {

  form = this.formBuilder.group({
    _id: [''],
    name: [''],
    category: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: CursosService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
    ) {
      const curso: Curso = this.route.snapshot.data['curso'];
      this.form.setValue({
        _id: curso._id,
        name: curso.name,
        category: curso.category
      })
    }

  onSubmit() {
    this.service.save(this.form.value).subscribe(
      success => this.onSuccess(),
      error => this.onError());
  }
  onCancel() {
    this.location.back();
  }

  private onError() {
    this.snackBar.open("Erro ao tentar salvar o curso.", '', {duration: 5000});
  }

  private onSuccess() {
    this.snackBar.open("Curso salvo com sucesso!", '', {duration: 5000});
    this.onCancel();
  }
}
