package br.rnp.redesegura.controllers;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.DASHBOARD)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

}
