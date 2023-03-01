package kg.megacom.authorizationservice.services.impl;

import kg.megacom.authorizationservice.dao.AccountRep;
import kg.megacom.authorizationservice.exceptions.AccountNotFoundExc;
import kg.megacom.authorizationservice.exceptions.AuthExc;
import kg.megacom.authorizationservice.mappers.AccountMapper;
import kg.megacom.authorizationservice.models.dtos.AccountDto;
import kg.megacom.authorizationservice.models.enums.AccountStatus;
import kg.megacom.authorizationservice.models.request.AuthRequest;
import kg.megacom.authorizationservice.services.AccountService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRep rep;

    public AccountServiceImpl(AccountRep rep) {
        this.rep = rep;
    }

    @Override
    public AccountDto save(AccountDto accountDto) {
        return AccountMapper.INSTANCE.toDto(rep.save(AccountMapper.INSTANCE.toEntity(accountDto)));
    }

    @Override
    public AccountDto findById(Long id) {
        return AccountMapper.INSTANCE.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Аккаунт не найден")));
    }

    @Override
    public AccountDto delete(Long id) {
        AccountDto account=findById(id);
        account.setActive(false);
        return save(account);
    }

    @Override
    public List<AccountDto> findAll() {
        return AccountMapper.INSTANCE.toDtos(rep.findAll());
    }



    @Override
    public String auth(AuthRequest request) {
        AccountDto account = AccountMapper.INSTANCE.toDto(rep.findByLogin(request.getLogin()).orElseThrow(()->new AccountNotFoundExc("Пользователь не найден")));
        switch (account.getStatus()){
            case ACTIVE:
                break;
            case BLOCKED:
                if (checkDate(account.getUpdateDate())){
                    account.setCount(0);
                    account.setStatus(AccountStatus.ACTIVE);
                    break;
                }
                throw new AuthExc("Ваш аккаунт заблокирован");
            case DELETED:
                throw new AuthExc("Ваш аккаунт удален");
        }
        if (account.getPassword().equals(request.getPassword())){
            account.setCount(0);
            save(account);
            return "Success";
        }else {

            if (account.getCount()>=3){
                account.setStatus(AccountStatus.BLOCKED);
                save(account);
                throw new AuthExc("Вы заблокированы.Повторите попытку через час");
            }
            account.setCount(account.getCount()+1);
            save(account);
            throw new AuthExc("Вы ввели неверный пароль");
        }

    }

    @Override
    public String create(AuthRequest request) {
        try {
            if (request.getLogin() == null || request.getLogin().isEmpty()){
                throw new RuntimeException("Логин не может быть пустым");
            }

            AccountDto accountDto = new AccountDto();
            accountDto.setLogin(request.getLogin());
            accountDto.setPassword(request.getPassword());
            save(accountDto);
            return "Success";
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Аккаунт с таким логином уже существует");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении аккаунта");
        }

    }

    private boolean checkDate(Date updateDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(updateDate);
        calendar.add(Calendar.HOUR, 1);
        return new Date().after(calendar.getTime());
    }
}
