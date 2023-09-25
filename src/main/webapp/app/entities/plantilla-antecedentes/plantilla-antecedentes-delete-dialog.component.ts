import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantillaAntecedentes } from 'app/shared/model/plantilla-antecedentes.model';
import { PlantillaAntecedentesService } from './plantilla-antecedentes.service';

@Component({
    selector: 'jhi-plantilla-antecedentes-delete-dialog',
    templateUrl: './plantilla-antecedentes-delete-dialog.component.html'
})
export class PlantillaAntecedentesDeleteDialogComponent {
    plantillaAntecedentes: IPlantillaAntecedentes;

    constructor(
        protected plantillaAntecedentesService: PlantillaAntecedentesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaAntecedentesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaAntecedentesListModification',
                content: 'Deleted an plantillaAntecedentes'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-antecedentes-delete-popup',
    template: ''
})
export class PlantillaAntecedentesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaAntecedentes }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaAntecedentesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plantillaAntecedentes = plantillaAntecedentes;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/plantilla-antecedentes', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/plantilla-antecedentes', { outlets: { popup: null } }]);
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
