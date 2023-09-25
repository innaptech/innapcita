import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlantillaRecipe } from 'app/shared/model/plantilla-recipe.model';

@Component({
    selector: 'jhi-plantilla-recipe-detail',
    templateUrl: './plantilla-recipe-detail.component.html'
})
export class PlantillaRecipeDetailComponent implements OnInit {
    plantillaRecipe: IPlantillaRecipe;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaRecipe }) => {
            this.plantillaRecipe = plantillaRecipe;
        });
    }

    previousState() {
        window.history.back();
    }
}
