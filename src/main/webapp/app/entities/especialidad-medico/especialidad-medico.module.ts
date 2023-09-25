import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import {AutocompleteLibModule} from 'angular-ng-autocomplete';

import { InnapcitasSharedModule } from 'app/shared';
import {
    EspecialidadMedicoComponent,
    EspecialidadMedicoMedicoComponent,
    EspecialidadMedicoDetailComponent,
    EspecialidadMedicoUpdateComponent,
    EspecialidadMedicoDeletePopupComponent,
    EspecialidadMedicoDeleteDialogComponent,
    especialidadMedicoRoute,
    especialidadMedicoPopupRoute
} from './';

const ENTITY_STATES = [...especialidadMedicoRoute, ...especialidadMedicoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), AutocompleteLibModule],
    declarations: [
        EspecialidadMedicoComponent,
        EspecialidadMedicoMedicoComponent,
        EspecialidadMedicoDetailComponent,
        EspecialidadMedicoUpdateComponent,
        EspecialidadMedicoDeleteDialogComponent,
        EspecialidadMedicoDeletePopupComponent
    ],
    entryComponents: [
        EspecialidadMedicoComponent,
        EspecialidadMedicoMedicoComponent,
        EspecialidadMedicoUpdateComponent,
        EspecialidadMedicoDeleteDialogComponent,
        EspecialidadMedicoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEspecialidadMedicoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
