package com.link.librarymodule.base.mvvm.binding.command


import io.reactivex.functions.Function

/**
 * About : kelin的ResponseCommand
 * 执行的命令事件转换
 */
class ResponseCommand<T, R> {

    private var execute: BindingFunction<R>? = null
    private var function: Function<T, R>? = null
    private var canExecute: BindingFunction<Boolean>? = null

    /**
     * like [BindingCommand],but ResponseCommand can return result when command has executed!
     *
     * @param execute function to execute when event occur.
     */
    constructor(execute: BindingFunction<R>) {
        this.execute = execute
    }


    constructor(execute: Function<T, R>) {
        this.function = execute
    }


    constructor(execute: BindingFunction<R>, canExecute: BindingFunction<Boolean>) {
        this.execute = execute
        this.canExecute = canExecute
    }


    constructor(execute: Function<T, R>, canExecute: BindingFunction<Boolean>) {
        this.function = execute
        this.canExecute = canExecute
    }


    fun execute(): R? {
        return if (execute != null && canExecute()) {
            execute!!.call()
        } else null
    }

    private fun canExecute(): Boolean {
        return if (canExecute == null) {
            true
        } else canExecute!!.call()
    }


    @Throws(Exception::class)
    fun execute(parameter: T): R? {
        return if (function != null && canExecute()) {
            function!!.apply(parameter)
        } else null
    }
}
