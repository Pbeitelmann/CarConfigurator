package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.CarConfigurationService;
import com.computacenter.carconfig.internal.ConfigurationId;
import com.computacenter.carconfig.internal.SpecialEquipmentId;
import com.computacenter.carconfig.internal.UserId;
import static java.lang.String.format;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/", "/index"})
@Controller
@RequiredArgsConstructor
public class UglyCarConfigurationController {

    private final CarConfigurationRequestMapper carConfigurationRequestMapper;
    private final CarConfigurationService carConfigurationService;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("carConfigurationRequest", CarConfigurationRequest.builder().build());

        model.addAttribute("engines", carConfigurationService.getEngines());
        model.addAttribute("paintWorks", carConfigurationService.getPaintWorks());
        model.addAttribute("rims", carConfigurationService.getRims());
        model.addAttribute("specialEquipment", carConfigurationService.getSpecialEquipment());

        return "index";
    }

    @GetMapping("/summary")
    public String summary(CarConfigurationRequest carConfigurationRequest, Model model) {
        var summary = carConfigurationService.getSummaryForRequest(carConfigurationRequest);
        var specialEquipmentIds = carConfigurationRequest.getSpecialEquipmentIds().stream()
                .map(SpecialEquipmentId::of).toList();

        model.addAttribute("summary", summary);
        model.addAttribute("carConfigurationRequest", carConfigurationRequest);
        model.addAttribute("specialEquipment", carConfigurationService.getSpecialEquipmentForSpecialEquipmentIds(specialEquipmentIds));

        return "order";
    }

    @GetMapping("/summary/{configurationId}")
    public String getSummary(@PathVariable("configurationId") String configurationId, Model model) {
        var savedSummary = carConfigurationService.getSummaryForConfigurationId(ConfigurationId.of(configurationId));

        model.addAttribute("savedSummary", savedSummary);
        model.addAttribute("specialEquipment", savedSummary.specialEquipmentNames);

        return "savedsummary";
    }

    @PostMapping("/order")
    public String orderConfiguration(CarConfigurationRequest carConfigurationRequest, Model model) {
        var carConfigurationIdOverview = carConfigurationRequestMapper.toConfiguration(carConfigurationRequest);
        var carConfigurationId = carConfigurationService.createCarConfiguration(carConfigurationIdOverview);

        model.addAttribute("feSummaryLink", format("/summary/%s", carConfigurationId.getValue()));
        model.addAttribute("beSummaryLink", format("/cc-backend/api/car/configuration/%s", carConfigurationId.getValue()));

        var userId = UserId.of("UID-001");
        carConfigurationService.orderConfiguration(userId, carConfigurationId);

        return "saved";
    }
}