import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cita } from 'app/shared/model/cita.model';
import { CitaService } from './cita.service';
import { AccountService, UserService, User } from 'app/core';
import { CitaComponent } from './cita.component';
import { CitaUserComponent } from './cita-user.component';
import { CitaPacienteComponent } from './cita-paciente.component';
import { CitaPacienteDetailComponent } from './cita-paciente-detail.component';
import { CitaDetailComponent } from './cita-detail.component';
import { CitaUpdateComponent } from './cita-update.component';
import { CitaDeletePopupComponent } from './cita-delete-dialog.component';
import { ICita } from 'app/shared/model/cita.model';

@Injectable({ providedIn: 'root' })
export class CitaResolve implements Resolve<ICita> {
    constructor(private service: CitaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICita> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Cita>) => response.ok),
                map((cita: HttpResponse<Cita>) => cita.body)
            );
        }
        return of(new Cita());
    }
}

@Injectable({ providedIn: 'root' })
export class UserResolve implements Resolve<any> {
    constructor(private service: UserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['login'] ? route.params['login'] : null;
        if (id) {
            return this.service.getPatient(id);
        }
        return new User();
    }
}

export const citaRoute: Routes = [
    {
        path: '',
        component: CitaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.cita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user',
        component: CitaUserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.cita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente',
        component: CitaPacienteComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_MEDICO', 'ROLE_ADMIN', 'ROLE_RECEPCION'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.global.menu.entities.pacientes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'paciente-detail/:login/view',
        component: CitaPacienteDetailComponent,
        resolve: {
            user: UserResolve
        },
        data: {
            authorities: ['ROLE_MEDICO', 'ROLE_ADMIN', 'ROLE_RECEPCION'],
            pageTitle: 'innapcitasApp.global.menu.entities.pacientes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CitaDetailComponent,
        resolve: {
            cita: CitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.cita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CitaUpdateComponent,
        resolve: {
            cita: CitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.cita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new/:medico',
        component: CitaUpdateComponent,
        resolve: {
            cita: CitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.cita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CitaUpdateComponent,
        resolve: {
            cita: CitaResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'innapcitasApp.cita.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const citaPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CitaDeletePopupComponent,
        resolve: {
            cita: CitaResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'innapcitasApp.cita.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
