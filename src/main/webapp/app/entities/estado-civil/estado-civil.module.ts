import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    EstadoCivilComponent,
    EstadoCivilDetailComponent,
    EstadoCivilUpdateComponent,
    EstadoCivilDeletePopupComponent,
    EstadoCivilDeleteDialogComponent,
    estadoCivilRoute,
    estadoCivilPopupRoute
} from './';

const ENTITY_STATES = [...estadoCivilRoute, ...estadoCivilPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EstadoCivilComponent,
        EstadoCivilDetailComponent,
        EstadoCivilUpdateComponent,
        EstadoCivilDeleteDialogComponent,
        EstadoCivilDeletePopupComponent
    ],
    entryComponents: [EstadoCivilComponent, EstadoCivilUpdateComponent, EstadoCivilDeleteDialogComponent, EstadoCivilDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEstadoCivilModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
