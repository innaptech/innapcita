import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { SpeechRecognitionModule, RxSpeechRecognitionService } from '@kamiazya/ngx-speech-recognition';

import { InnapcitasSharedModule } from 'app/shared';
import {
    HistoriaPersonalAdultoComponent,
    HistoriaPersonalAdultoDetailComponent,
    HistoriaPersonalAdultoUpdateComponent,
    HistoriaPersonalAdultoDeletePopupComponent,
    HistoriaPersonalAdultoDeleteDialogComponent,
    historiaPersonalAdultoRoute,
    historiaPersonalAdultoPopupRoute
} from './';

const ENTITY_STATES = [...historiaPersonalAdultoRoute, ...historiaPersonalAdultoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), SpeechRecognitionModule.withConfig({
                                                                                      lang: 'es-VE',
                                                                                      interimResults: true,
                                                                                      maxAlternatives: 10,
                                                                                    })],
    declarations: [
        HistoriaPersonalAdultoComponent,
        HistoriaPersonalAdultoDetailComponent,
        HistoriaPersonalAdultoUpdateComponent,
        HistoriaPersonalAdultoDeleteDialogComponent,
        HistoriaPersonalAdultoDeletePopupComponent
    ],
    entryComponents: [
        HistoriaPersonalAdultoComponent,
        HistoriaPersonalAdultoUpdateComponent,
        HistoriaPersonalAdultoDeleteDialogComponent,
        HistoriaPersonalAdultoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }, RxSpeechRecognitionService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasHistoriaPersonalAdultoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
