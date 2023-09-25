import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    EspecialidadComponent,
    EspecialidadDetailComponent,
    EspecialidadUpdateComponent,
    EspecialidadDeletePopupComponent,
    EspecialidadDeleteDialogComponent,
    especialidadRoute,
    especialidadPopupRoute
} from './';

const ENTITY_STATES = [...especialidadRoute, ...especialidadPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EspecialidadComponent,
        EspecialidadDetailComponent,
        EspecialidadUpdateComponent,
        EspecialidadDeleteDialogComponent,
        EspecialidadDeletePopupComponent
    ],
    entryComponents: [
        EspecialidadComponent,
        EspecialidadUpdateComponent,
        EspecialidadDeleteDialogComponent,
        EspecialidadDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEspecialidadModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
