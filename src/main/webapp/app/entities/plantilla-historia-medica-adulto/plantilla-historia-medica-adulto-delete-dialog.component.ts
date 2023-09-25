import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantillaHistoriaMedicaAdulto } from 'app/shared/model/plantilla-historia-medica-adulto.model';
import { PlantillaHistoriaMedicaAdultoService } from './plantilla-historia-medica-adulto.service';

@Component({
    selector: 'jhi-plantilla-historia-medica-adulto-delete-dialog',
    templateUrl: './plantilla-historia-medica-adulto-delete-dialog.component.html'
})
export class PlantillaHistoriaMedicaAdultoDeleteDialogComponent {
    plantillaHistoriaMedicaAdulto: IPlantillaHistoriaMedicaAdulto;

    constructor(
        protected plantillaHistoriaMedicaAdultoService: PlantillaHistoriaMedicaAdultoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaHistoriaMedicaAdultoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaHistoriaMedicaAdultoListModification',
                content: 'Deleted an plantillaHistoriaMedicaAdulto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-historia-medica-adulto-delete-popup',
    template: ''
})
export class PlantillaHistoriaMedicaAdultoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaMedicaAdulto }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaHistoriaMedicaAdultoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plantillaHistoriaMedicaAdulto = plantillaHistoriaMedicaAdulto;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/plantilla-historia-medica-adulto', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/plantilla-historia-medica-adulto', { outlets: { popup: null } }]);
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
