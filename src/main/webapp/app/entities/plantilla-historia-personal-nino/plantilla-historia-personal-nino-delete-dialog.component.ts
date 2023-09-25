import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantillaHistoriaPersonalNino } from 'app/shared/model/plantilla-historia-personal-nino.model';
import { PlantillaHistoriaPersonalNinoService } from './plantilla-historia-personal-nino.service';

@Component({
    selector: 'jhi-plantilla-historia-personal-nino-delete-dialog',
    templateUrl: './plantilla-historia-personal-nino-delete-dialog.component.html'
})
export class PlantillaHistoriaPersonalNinoDeleteDialogComponent {
    plantillaHistoriaPersonalNino: IPlantillaHistoriaPersonalNino;

    constructor(
        protected plantillaHistoriaPersonalNinoService: PlantillaHistoriaPersonalNinoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaHistoriaPersonalNinoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaHistoriaPersonalNinoListModification',
                content: 'Deleted an plantillaHistoriaPersonalNino'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-historia-personal-nino-delete-popup',
    template: ''
})
export class PlantillaHistoriaPersonalNinoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaPersonalNino }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaHistoriaPersonalNinoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plantillaHistoriaPersonalNino = plantillaHistoriaPersonalNino;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/plantilla-historia-personal-nino', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/plantilla-historia-personal-nino', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
