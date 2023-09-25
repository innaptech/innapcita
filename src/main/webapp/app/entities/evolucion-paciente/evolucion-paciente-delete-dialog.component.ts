import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';
import { EvolucionPacienteService } from './evolucion-paciente.service';

@Component({
    selector: 'jhi-evolucion-paciente-delete-dialog',
    templateUrl: './evolucion-paciente-delete-dialog.component.html'
})
export class EvolucionPacienteDeleteDialogComponent {
    evolucionPaciente: IEvolucionPaciente;

    constructor(
        protected evolucionPacienteService: EvolucionPacienteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.evolucionPacienteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'evolucionPacienteListModification',
                content: 'Deleted an evolucionPaciente'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-evolucion-paciente-delete-popup',
    template: ''
})
export class EvolucionPacienteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evolucionPaciente }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EvolucionPacienteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.evolucionPaciente = evolucionPaciente;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/evolucion-paciente', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/evolucion-paciente', { outlets: { popup: null } }]);
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
