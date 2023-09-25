import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    TipoConsultaComponent,
    TipoConsultaDetailComponent,
    TipoConsultaUpdateComponent,
    TipoConsultaDeletePopupComponent,
    TipoConsultaDeleteDialogComponent,
    tipoConsultaRoute,
    tipoConsultaPopupRoute
} from './';

const ENTITY_STATES = [...tipoConsultaRoute, ...tipoConsultaPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoConsultaComponent,
        TipoConsultaDetailComponent,
        TipoConsultaUpdateComponent,
        TipoConsultaDeleteDialogComponent,
        TipoConsultaDeletePopupComponent
    ],
    entryComponents: [
        TipoConsultaComponent,
        TipoConsultaUpdateComponent,
        TipoConsultaDeleteDialogComponent,
        TipoConsultaDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasTipoConsultaModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
