import { Component } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { CursosService } from '../../services/cursos.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Curso } from '../../model/curso';
import { Aula } from '../../model/aula';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss']
})
export class CursoFormComponent {

  form!: FormGroup;

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: CursosService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
    ) {
      const curso: Curso = this.route.snapshot.data['curso'];
      this.form = this.formBuilder.group({
          _id: [curso._id],
          name: [curso.name, [Validators.required,
                      Validators.minLength(5),
                      Validators.maxLength(100)]],
          category: [curso.category, [Validators.required]],
          aulas: this.formBuilder.array(this.obterAulas(curso))
        });
    }

    private obterAulas(curso: Curso) {
      const aulas = [];
      if(curso?.aulas) {
        curso.aulas.forEach(aula => aulas.push(this.criarAula(aula)));
      } else {
        aulas.push(this.criarAula());
      }
      return aulas;
    }

    private criarAula(aula: Aula = {id: '', name: '', urlYoutube: ''}) {
     return this.formBuilder.group({
        id: [aula.id],
        name: [aula.name],
        urlYoutube: [aula.urlYoutube]
      });
    }

    getAulasFormArray() {
      return (<UntypedFormArray>this.form.get('aulas')).controls;
    }

    addNovaAula() {
      const aulas = this.form.get('aulas') as UntypedFormArray;
      aulas.push(this.criarAula());
    }

    removerAula(index: number) {
      const aula = this.form.get('aulas') as UntypedFormArray;
      aula.removeAt(index);
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

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if(field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if(field?.hasError('minlength')) {
      const minLength = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${minLength} caracteres`;
    }

    if(field?.hasError('maxlength')) {
      const maxLength = field.errors ? field.errors['maxlength']['requiredLength'] : 100;
      return `Tamanho máximo excedido de ${maxLength} caracteres`;
    }
     return 'Campo inválido';
  }
}
