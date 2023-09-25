import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    SexoComponent,
    SexoDetailComponent,
    SexoUpdateComponent,
    SexoDeletePopupComponent,
    SexoDeleteDialogComponent,
    sexoRoute,
    sexoPopupRoute
} from './';

const ENTITY_STATES = [...sexoRoute, ...sexoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [SexoComponent, SexoDetailComponent, SexoUpdateComponent, SexoDeleteDialogComponent, SexoDeletePopupComponent],
    entryComponents: [SexoComponent, SexoUpdateComponent, SexoDeleteDialogComponent, SexoDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasSexoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
