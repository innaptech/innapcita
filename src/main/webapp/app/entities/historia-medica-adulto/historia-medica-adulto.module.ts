import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { SpeechRecognitionModule, RxSpeechRecognitionService } from '@kamiazya/ngx-speech-recognition';

import { InnapcitasSharedModule } from 'app/shared';
import {
    HistoriaMedicaAdultoComponent,
    HistoriaMedicaAdultoDetailComponent,
    HistoriaMedicaAdultoUpdateComponent,
    HistoriaMedicaAdultoDeletePopupComponent,
    HistoriaMedicaAdultoDeleteDialogComponent,
    historiaMedicaAdultoRoute,
    historiaMedicaAdultoPopupRoute
} from './';

const ENTITY_STATES = [...historiaMedicaAdultoRoute, ...historiaMedicaAdultoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), SpeechRecognitionModule ? SpeechRecognitionModule.withConfig({
                                                                                    lang: 'es-VE',
                                                                                    interimResults: true,
                                                                                    maxAlternatives: 10,
                                                                                  }) : undefined],
    declarations: [
        HistoriaMedicaAdultoComponent,
        HistoriaMedicaAdultoDetailComponent,
        HistoriaMedicaAdultoUpdateComponent,
        HistoriaMedicaAdultoDeleteDialogComponent,
        HistoriaMedicaAdultoDeletePopupComponent
    ],
    entryComponents: [
        HistoriaMedicaAdultoComponent,
        HistoriaMedicaAdultoUpdateComponent,
        HistoriaMedicaAdultoDeleteDialogComponent,
        HistoriaMedicaAdultoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }, RxSpeechRecognitionService ? RxSpeechRecognitionService : undefined],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasHistoriaMedicaAdultoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
