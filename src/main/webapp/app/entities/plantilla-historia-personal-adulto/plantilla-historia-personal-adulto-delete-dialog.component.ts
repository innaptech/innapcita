import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantillaHistoriaPersonalAdulto } from 'app/shared/model/plantilla-historia-personal-adulto.model';
import { PlantillaHistoriaPersonalAdultoService } from './plantilla-historia-personal-adulto.service';

@Component({
    selector: 'jhi-plantilla-historia-personal-adulto-delete-dialog',
    templateUrl: './plantilla-historia-personal-adulto-delete-dialog.component.html'
})
export class PlantillaHistoriaPersonalAdultoDeleteDialogComponent {
    plantillaHistoriaPersonalAdulto: IPlantillaHistoriaPersonalAdulto;

    constructor(
        protected plantillaHistoriaPersonalAdultoService: PlantillaHistoriaPersonalAdultoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaHistoriaPersonalAdultoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaHistoriaPersonalAdultoListModification',
                content: 'Deleted an plantillaHistoriaPersonalAdulto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-historia-personal-adulto-delete-popup',
    template: ''
})
export class PlantillaHistoriaPersonalAdultoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaPersonalAdulto }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaHistoriaPersonalAdultoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plantillaHistoriaPersonalAdulto = plantillaHistoriaPersonalAdulto;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/plantilla-historia-personal-adulto', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/plantilla-historia-personal-adulto', { outlets: { popup: null } }]);
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
