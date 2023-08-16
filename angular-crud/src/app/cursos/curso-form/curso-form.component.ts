import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CursosService } from '../services/cursos.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss']
})
export class CursoFormComponent {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private service: CursosService,
    private snackBar: MatSnackBar
    ) {
    this.form = this.formBuilder.group({
      name: [null],
      category: [null]
    });
  }

  onSubmit() {
    this.service.save(this.form.value).subscribe(
      data => console.log(data),
      error => this.onError());
  }
  onCancel() {}

  private onError() {
    this.snackBar.open("Erro ao tentar salvar o curso.", '', {duration: 5000});
  }
}
