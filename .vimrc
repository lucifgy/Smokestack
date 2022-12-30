set nocompatible                " choose no compatibility with legacy vi
syntax enable
set encoding=utf-8
set showcmd                     " display incomplete commands
filetype plugin indent on       " load file type plugins + indentation
set number						" Show line numbers
set wrap 						" Wrap text to fit the window
set linebreak 					" Don't break words wrapping
set nolist

set autoindent
set copyindent

set noexpandtab 
set tabstop=4 shiftwidth=4      " a tab is two spaces (or set this to 4)
set backspace=indent,eol,start "backspace through everything in insert mode

set showmatch
set smarttab

set scrolloff=4

colorscheme koehler

"" Searching
set hlsearch                    " highlight matches
set incsearch                   " incremental searching
set ignorecase                  " searches are case insensitive...
set smartcase " ... unless they contain at least one capital letter
command CompileNrun !gcc % && ./a.out
map <F9> = :CompileNrun
