import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    DiaSemanaComponent,
    DiaSemanaDetailComponent,
    DiaSemanaUpdateComponent,
    DiaSemanaDeletePopupComponent,
    DiaSemanaDeleteDialogComponent,
    diaSemanaRoute,
    diaSemanaPopupRoute
} from './';

const ENTITY_STATES = [...diaSemanaRoute, ...diaSemanaPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DiaSemanaComponent,
        DiaSemanaDetailComponent,
        DiaSemanaUpdateComponent,
        DiaSemanaDeleteDialogComponent,
        DiaSemanaDeletePopupComponent
    ],
    entryComponents: [DiaSemanaComponent, DiaSemanaUpdateComponent, DiaSemanaDeleteDialogComponent, DiaSemanaDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasDiaSemanaModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
