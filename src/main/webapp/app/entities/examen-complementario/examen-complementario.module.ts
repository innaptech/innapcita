import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    ExamenComplementarioComponent,
    ExamenComplementarioDetailComponent,
    ExamenComplementarioUpdateComponent,
    ExamenComplementarioDeletePopupComponent,
    ExamenComplementarioDeleteDialogComponent,
    examenComplementarioRoute,
    examenComplementarioPopupRoute
} from './';

const ENTITY_STATES = [...examenComplementarioRoute, ...examenComplementarioPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExamenComplementarioComponent,
        ExamenComplementarioDetailComponent,
        ExamenComplementarioUpdateComponent,
        ExamenComplementarioDeleteDialogComponent,
        ExamenComplementarioDeletePopupComponent
    ],
    entryComponents: [
        ExamenComplementarioComponent,
        ExamenComplementarioUpdateComponent,
        ExamenComplementarioDeleteDialogComponent,
        ExamenComplementarioDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasExamenComplementarioModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
