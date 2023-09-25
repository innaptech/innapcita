import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPlantillaRecipe } from 'app/shared/model/plantilla-recipe.model';
import { PlantillaRecipeService } from './plantilla-recipe.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-plantilla-recipe-update',
    templateUrl: './plantilla-recipe-update.component.html'
})
export class PlantillaRecipeUpdateComponent implements OnInit {
    plantillaRecipe: IPlantillaRecipe;
    isSaving: boolean;

    users: IUser[];

    valores: any[];

    recipe: any = {
            pacienteNombre: 'a',
            pacienteFechaNacimiento: 'a',
            pacienteCedula: 'a',
            fechaActual: 'a',
            texto: 'a',
            selloMedico: 'a',
    };

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected plantillaRecipeService: PlantillaRecipeService,
        protected userService: UserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantillaRecipe }) => {
            this.plantillaRecipe = plantillaRecipe;
        });
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.valores = Object.keys(this.recipe);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantillaRecipe.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaRecipeService.update(this.plantillaRecipe));
        } else {
            this.subscribeToSaveResponse(this.plantillaRecipeService.create(this.plantillaRecipe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlantillaRecipe>>) {
        result.subscribe((res: HttpResponse<IPlantillaRecipe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
