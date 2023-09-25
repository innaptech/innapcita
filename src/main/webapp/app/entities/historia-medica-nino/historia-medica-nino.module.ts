import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { SpeechRecognitionModule, RxSpeechRecognitionService } from '@kamiazya/ngx-speech-recognition';

import { InnapcitasSharedModule } from 'app/shared';
import {
    HistoriaMedicaNinoComponent,
    HistoriaMedicaNinoDetailComponent,
    HistoriaMedicaNinoUpdateComponent,
    HistoriaMedicaNinoDeletePopupComponent,
    HistoriaMedicaNinoDeleteDialogComponent,
    historiaMedicaNinoRoute,
    historiaMedicaNinoPopupRoute
} from './';

const ENTITY_STATES = [...historiaMedicaNinoRoute, ...historiaMedicaNinoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), SpeechRecognitionModule.withConfig({
                                                                                    lang: 'es-VE',
                                                                                    interimResults: true,
                                                                                    maxAlternatives: 10,
                                                                                  })],
    declarations: [
        HistoriaMedicaNinoComponent,
        HistoriaMedicaNinoDetailComponent,
        HistoriaMedicaNinoUpdateComponent,
        HistoriaMedicaNinoDeleteDialogComponent,
        HistoriaMedicaNinoDeletePopupComponent
    ],
    entryComponents: [
        HistoriaMedicaNinoComponent,
        HistoriaMedicaNinoUpdateComponent,
        HistoriaMedicaNinoDeleteDialogComponent,
        HistoriaMedicaNinoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }, RxSpeechRecognitionService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasHistoriaMedicaNinoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
