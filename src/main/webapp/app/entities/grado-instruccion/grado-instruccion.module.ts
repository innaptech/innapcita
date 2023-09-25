import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    GradoInstruccionComponent,
    GradoInstruccionDetailComponent,
    GradoInstruccionUpdateComponent,
    GradoInstruccionDeletePopupComponent,
    GradoInstruccionDeleteDialogComponent,
    gradoInstruccionRoute,
    gradoInstruccionPopupRoute
} from './';

const ENTITY_STATES = [...gradoInstruccionRoute, ...gradoInstruccionPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GradoInstruccionComponent,
        GradoInstruccionDetailComponent,
        GradoInstruccionUpdateComponent,
        GradoInstruccionDeleteDialogComponent,
        GradoInstruccionDeletePopupComponent
    ],
    entryComponents: [
        GradoInstruccionComponent,
        GradoInstruccionUpdateComponent,
        GradoInstruccionDeleteDialogComponent,
        GradoInstruccionDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasGradoInstruccionModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
