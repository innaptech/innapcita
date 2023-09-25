import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { SpeechRecognitionModule, RxSpeechRecognitionService } from '@kamiazya/ngx-speech-recognition';

import { InnapcitasSharedModule } from 'app/shared';
import {
    HistoriaPersonalNinoComponent,
    HistoriaPersonalNinoDetailComponent,
    HistoriaPersonalNinoUpdateComponent,
    HistoriaPersonalNinoDeletePopupComponent,
    HistoriaPersonalNinoDeleteDialogComponent,
    historiaPersonalNinoRoute,
    historiaPersonalNinoPopupRoute
} from './';

const ENTITY_STATES = [...historiaPersonalNinoRoute, ...historiaPersonalNinoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), SpeechRecognitionModule.withConfig({
                                                                                lang: 'es-VE',
                                                                                interimResults: true,
                                                                                maxAlternatives: 10,
                                                                              })],
    declarations: [
        HistoriaPersonalNinoComponent,
        HistoriaPersonalNinoDetailComponent,
        HistoriaPersonalNinoUpdateComponent,
        HistoriaPersonalNinoDeleteDialogComponent,
        HistoriaPersonalNinoDeletePopupComponent
    ],
    entryComponents: [
        HistoriaPersonalNinoComponent,
        HistoriaPersonalNinoUpdateComponent,
        HistoriaPersonalNinoDeleteDialogComponent,
        HistoriaPersonalNinoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }, RxSpeechRecognitionService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasHistoriaPersonalNinoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
