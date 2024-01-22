package com.example.demospringboot.controller;

import com.example.demospringboot.model.Department;
import com.example.demospringboot.model.Employee;
import com.example.demospringboot.service.IDepartmentService;
import com.example.demospringboot.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ViewController {
    private final IEmployeeService employeeService;
    private final IDepartmentService departmentService;

    @GetMapping
    public ModelAndView index(@RequestParam(required = false, defaultValue = "") String searchText,
                              @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC ) Pageable pageable) {

        Page<Employee> employeePage = employeePage = employeeService.findByNameOrEmail(pageable, searchText);
//        if (searchText != null) {
//            if (!searchText.equals("null")) {
//
//            } else {
//                employeePage = employeeService.findAll(pageable);
//            }
//        } else {
//            employeePage = employeeService.findAll(pageable);
//        }

        ModelAndView modelAndView = new ModelAndView("employee/index");
        modelAndView.addObject("searchText", searchText);
        modelAndView.addObject("employees", employeePage);
        modelAndView.addObject("department", new Department());


        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("departments", departmentService.findAll());
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create-department")
    public ModelAndView createDepartment(@ModelAttribute Department department,
                                         RedirectAttributes redirect) {
        departmentService.create(department);
        redirect.addFlashAttribute("message", "Thêm mới phòng ban thành công");
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Employee employee,
                               RedirectAttributes redirect) {
        try {
            employeeService.create(employee);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("employee/create");
            String dob = employeeService.formatDob(employee);
            modelAndView.addObject("departments", departmentService.findAll());
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("dob", dob);
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }

        redirect.addFlashAttribute("message", "Thêm mới nhân viên thành công");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        Employee employee = employeeService.findById(id);
        String dob = employeeService.formatDob(employee);
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("dob", dob);
        modelAndView.addObject("departments", departmentService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Employee employee,
                             @RequestParam Date dob,
                             RedirectAttributes redirect) {
        employeeService.update(employee);
        redirect.addFlashAttribute("message", "Cập nhật nhân viên thành công");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id,
                               RedirectAttributes redirect) {
        employeeService.removeById(id);
        redirect.addFlashAttribute("message", "Xoá nhân viên thành công");
        return new ModelAndView("redirect:/");
    }

}
