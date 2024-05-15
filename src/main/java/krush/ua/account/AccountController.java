package krush.ua.account;

import jakarta.validation.Valid;
import krush.ua.account.dtos.SignInDTO;
import krush.ua.account.dtos.SignUpDTO;
import krush.ua.technical.attribute.ModelAttributeManager;
import krush.ua.technical.attribute.ModelPageAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/technical/login-page")
    public String getLoginPage(Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Login")
                .content("login-page")
                .entity(new SignInDTO())
                .build());
        return "layout";
    }

    @GetMapping("/technical/registration-page")
    public String getRegistrationPage(Model model) {
        ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                .title("Sign Up")
                .content("registration-page")
                .entity(new SignUpDTO())
                .build());

        return "layout";
    }

    @PostMapping("/technical/registration")
    public String registration(@Valid @ModelAttribute("entity") SignUpDTO dto,
                               BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                    .title("Sign Up")
                    .content("registration-page")
                    .entity(dto)
                    .build());

            return "layout";
        }

        if(accountService.loginIsExist(dto.getEmail()))
        {
            ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                    .title("Sign Up")
                    .content("registration-page")
                    .entity(dto)
                    .build());
            bindingResult.rejectValue("login", "error","Така адреса вже існує");
            return "layout";
        }

        if(accountService.registration(dto)){
            ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                    .title("Login")
                    .content("login-page")
                    .entity(new SignInDTO())
                    .build());
        }
        else{
            ModelAttributeManager.setModelAttribute(model, ModelPageAttributes.builder()
                    .title("Sign Up")
                    .content("registration-page")
                    .entity(dto)
                    .build());
            bindingResult.rejectValue("secretKey", "error","секретний ключ не вірний");
        }

        return "layout";
    }
}
