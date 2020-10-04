package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccountList());
        return "bankaccount";
    }

    @PostMapping
    public String openBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.createBankAccount(bankAccount);
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccountList());
        return "redirect:bankaccount";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-withdraw";
    }

    @GetMapping("/deposit/{id}")
    public String getDepositBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-deposit";
    }

//    @PostMapping("/edit/{id}")
//    public String editAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
//        bankAccountService.editBankAccount(bankAccount);
//        model.addAttribute("allBankAccounts", bankAccountService.getBankAccountList());
//        return "redirect:/bankaccount";
//    }

    @PostMapping("/withdraw/{id}")
    public String withdrawInAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.withdraw(id, bankAccount);
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccountList());
        return "redirect:/bankaccount";
    }

    @PostMapping("/deposit/{id}")
    public String depositInAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.deposit(id, bankAccount);
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccountList());
        return "redirect:/bankaccount";
    }


    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankAccount", bankAccountService.getBankAccountList());
        return "redirect:/bankaccount";

    }

}
