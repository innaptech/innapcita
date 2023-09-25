import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PlantillaRecipeComponent,
    PlantillaRecipeDetailComponent,
    PlantillaRecipeUpdateComponent,
    PlantillaRecipeDeletePopupComponent,
    PlantillaRecipeDeleteDialogComponent,
    plantillaRecipeRoute,
    plantillaRecipePopupRoute
} from './';

const ENTITY_STATES = [...plantillaRecipeRoute, ...plantillaRecipePopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        PlantillaRecipeComponent,
        PlantillaRecipeDetailComponent,
        PlantillaRecipeUpdateComponent,
        PlantillaRecipeDeleteDialogComponent,
        PlantillaRecipeDeletePopupComponent
    ],
    entryComponents: [
        PlantillaRecipeComponent,
        PlantillaRecipeUpdateComponent,
        PlantillaRecipeDeleteDialogComponent,
        PlantillaRecipeDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPlantillaRecipeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
